import { Component } from '@angular/core';

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
}
