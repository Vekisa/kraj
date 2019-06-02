import { TestBed } from '@angular/core/testing';

import { ExtraOptionsService } from './extra-options.service';

describe('ExtraOptionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExtraOptionsService = TestBed.get(ExtraOptionsService);
    expect(service).toBeTruthy();
  });
});
