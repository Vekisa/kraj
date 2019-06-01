import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectTypesComponent } from './object-types.component';

describe('ObjectTypesComponent', () => {
  let component: ObjectTypesComponent;
  let fixture: ComponentFixture<ObjectTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
