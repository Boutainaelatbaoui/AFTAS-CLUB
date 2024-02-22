import { Component } from '@angular/core';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  getImageUrl(): string {
    return 'assets/img/Spearfishing.jpg';
  }
  imageSection: string = 'assets/img/Hunt.png';
  imageSection1: string = 'assets/img/Hunt1.png';

  constructor(private storageService : StorageService) {}

  user = this.storageService.getSavedUser();
  permissions = this.storageService.getPermissions();
  showAdminBoard = false;

  ngOnInit(): void {
    if(this.user) {
      console.log(this.user);
      
      this.showAdminBoard = this.permissions.includes('CAN_MANAGE_COMPETITIONS');
    }
  }
}
