import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationTypesComponent } from './accommodation-types.component';

describe('AccommodationTypesComponent', () => {
  let component: AccommodationTypesComponent;
  let fixture: ComponentFixture<AccommodationTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
