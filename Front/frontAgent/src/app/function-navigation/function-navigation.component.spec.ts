import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunctionNavigationComponent } from './function-navigation.component';

describe('FunctionNavigationComponent', () => {
  let component: FunctionNavigationComponent;
  let fixture: ComponentFixture<FunctionNavigationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunctionNavigationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunctionNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
