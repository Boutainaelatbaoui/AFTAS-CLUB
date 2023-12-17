import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingTopComponent } from './ranking-top.component';

describe('RankingTopComponent', () => {
  let component: RankingTopComponent;
  let fixture: ComponentFixture<RankingTopComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RankingTopComponent]
    });
    fixture = TestBed.createComponent(RankingTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
