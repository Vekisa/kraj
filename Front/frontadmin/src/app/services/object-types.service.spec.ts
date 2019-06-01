import { TestBed } from '@angular/core/testing';

import { ObjectTypesService } from './object-types.service';

describe('ObjectTypesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ObjectTypesService = TestBed.get(ObjectTypesService);
    expect(service).toBeTruthy();
  });
});
