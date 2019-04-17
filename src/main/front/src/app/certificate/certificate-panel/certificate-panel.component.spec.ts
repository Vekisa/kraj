import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificatePanelComponent } from './certificate-panel.component';

describe('CertificatePanelComponent', () => {
  let component: CertificatePanelComponent;
  let fixture: ComponentFixture<CertificatePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CertificatePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CertificatePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
