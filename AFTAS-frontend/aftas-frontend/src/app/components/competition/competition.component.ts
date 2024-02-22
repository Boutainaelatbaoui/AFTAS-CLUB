import { Component, OnInit } from '@angular/core';
import { Competition } from 'src/app/models/competition';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { PageEvent } from '@angular/material/paginator';
import { CompetitionPage } from 'src/app/models/competition-page';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css'],
})
export class CompetitionComponent implements OnInit {
  competitions: Competition[] = [];
  filteredCompetitions: Competition[] = [];
  selectedFilter: string = '';
  imageSection2: string = 'assets/img/Hunt2.jpg';
  pageSizeOptions: number[] = [3, 5, 25, 100];
  totalItems = 0;
  currentPage = 0;
  pageSize = 3;

  constructor(private competitionService: CompetitionService, private storageService: StorageService) {}

  user = this.storageService.getSavedUser();
  permissions = this.storageService.getPermissions();
  showAdminBoard = false;

  ngOnInit(): void {
    if(this.user) {
      console.log(this.user);
      this.showAdminBoard = this.permissions.includes('CAN_MANAGE_COMPETITIONS');
      this.loadCompetitions();
    }
  }

  loadCompetitions(): void {
    this.competitionService.getAllPageCompetitions(this.currentPage, this.pageSize).subscribe(
      (response: CompetitionPage) => {
        console.log('Competitions:', response);
  
        this.competitions = response.content;
        this.filteredCompetitions = response.content;
        console.log(response.content);
        this.totalItems = response.totalElements;
      },
      (error) => {
        console.error('Error fetching competitions:', error);
      }
    );
  }
  

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadCompetitions();
  }


  filterCompetitions(): void {
    if (this.selectedFilter === 'en cours') {
      this.filteredCompetitions = this.competitions.filter(
        (competition) => this.isCompetitionInProgress(competition)        
      );
    } else if (this.selectedFilter === 'fermé') {
      this.filteredCompetitions = this.competitions.filter(
        (competition) => !this.isCompetitionInProgress(competition)
      );
    } else {
      this.filteredCompetitions = this.competitions;
    }
  }

  private isCompetitionInProgress(competition: Competition): boolean {
    const currentDate = new Date();
    const competitionDateTime = new Date(`${competition.date}T${competition.startTime}`);
    return currentDate < competitionDateTime;
  }

  selectFilter(filter: string): void {
    console.log('Filter selected:', filter);
    this.selectedFilter = filter;
    this.filterCompetitions();
  }
  
}
