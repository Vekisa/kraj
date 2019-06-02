import { TestBed } from '@angular/core/testing';

import { AccommodationTypesService } from './accommodation-types.service';

describe('AccommodationTypesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccommodationTypesService = TestBed.get(AccommodationTypesService);
    expect(service).toBeTruthy();
  });
});
