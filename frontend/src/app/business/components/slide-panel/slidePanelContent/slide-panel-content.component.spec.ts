import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlidePanelContentComponent } from './slide-panel-content.component';

describe('SlidePanelContentComponent', () => {
  let component: SlidePanelContentComponent;
  let fixture: ComponentFixture<SlidePanelContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlidePanelContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlidePanelContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
