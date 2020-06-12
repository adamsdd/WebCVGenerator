import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobExperienceModalComponent } from './job-experience-modal.component';

describe('JobExperienceModalComponent', () => {
  let component: JobExperienceModalComponent;
  let fixture: ComponentFixture<JobExperienceModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobExperienceModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobExperienceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
