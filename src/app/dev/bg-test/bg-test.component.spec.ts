import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BgTestComponent } from './bg-test.component';

describe('BgTestComponent', () => {
  let component: BgTestComponent;
  let fixture: ComponentFixture<BgTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BgTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BgTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
