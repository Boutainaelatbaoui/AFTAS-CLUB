import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuntingFormComponent } from './hunting-form.component';

describe('HuntingFormComponent', () => {
  let component: HuntingFormComponent;
  let fixture: ComponentFixture<HuntingFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HuntingFormComponent]
    });
    fixture = TestBed.createComponent(HuntingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
