import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RolepointComponent} from './rolepoint.component';

describe('RolepointComponent', () => {
  let component: RolepointComponent;
  let fixture: ComponentFixture<RolepointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RolepointComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RolepointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
