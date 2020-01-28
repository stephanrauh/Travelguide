import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContinentOverviewComponent } from './continent-overview.component';

describe('ContinentOverviewComponent', () => {
  let component: ContinentOverviewComponent;
  let fixture: ComponentFixture<ContinentOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContinentOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContinentOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
