import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoomedComponent } from './zoomed.component';

describe('ZoomedComponent', () => {
  let component: ZoomedComponent;
  let fixture: ComponentFixture<ZoomedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZoomedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZoomedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
