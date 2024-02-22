import { Component, AfterViewInit, OnDestroy } from '@angular/core';
import { HuntingService } from 'src/app/services/huntingService/hunting.service';
import { Hunting } from 'src/app/models/hunting';
import { StorageService } from 'src/app/services/storage/storage.service';
declare var $: any;

@Component({
    selector: 'app-hunting',
    templateUrl: './hunting.component.html',
    styleUrls: ['./hunting.component.css'],
})
export class HuntingComponent implements AfterViewInit, OnDestroy {
    huntings: Hunting[] = [];
    table: any;

    constructor(private huntingService: HuntingService, private storageService: StorageService) { }

    user = this.storageService.getSavedUser();
    permissions = this.storageService.getPermissions();
    showAdminBoard = false;

    ngOnInit(): void {
        if(this.user) {
        console.log(this.user);
        
        this.showAdminBoard = this.permissions.includes('CAN_MANAGE_COMPETITIONS');
        }
    }

    ngAfterViewInit(): void {
        this.huntingService.getAllHuntings().subscribe((data) => {
            this.huntings = data;
            this.initializeDataTable();
        });
    }

    ngOnDestroy(): void {
        if (this.table) {
            this.table.destroy();
        }
    }

    private initializeDataTable(): void {
        $(document).ready(() => {
            this.table = $('#huntingTable').DataTable({
                data: this.huntings,
                columns: [
                    { data: 'numberOfFish' },
                    { data: 'fish.name' },
                    { data: 'competition.code' },
                    { data: 'weight' },
                    { data: 'member.identityNumber' },
                ],
                searching: true,
            });
        });
    }
}
