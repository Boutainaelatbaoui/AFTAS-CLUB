import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberCompetitionsComponent } from './member-competitions.component';

describe('MemberCompetitionsComponent', () => {
  let component: MemberCompetitionsComponent;
  let fixture: ComponentFixture<MemberCompetitionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MemberCompetitionsComponent]
    });
    fixture = TestBed.createComponent(MemberCompetitionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
