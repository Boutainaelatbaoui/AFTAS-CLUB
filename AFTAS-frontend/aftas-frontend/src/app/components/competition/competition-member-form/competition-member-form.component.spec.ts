import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionMemberFormComponent } from './competition-member-form.component';

describe('CompetitionMemberFormComponent', () => {
  let component: CompetitionMemberFormComponent;
  let fixture: ComponentFixture<CompetitionMemberFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionMemberFormComponent]
    });
    fixture = TestBed.createComponent(CompetitionMemberFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
