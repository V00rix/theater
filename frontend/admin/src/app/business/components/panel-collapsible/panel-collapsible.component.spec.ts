import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelCollapsibleComponent } from './panel-collapsible.component';

describe('PanelCollapsibleComponent', () => {
  let component: PanelCollapsibleComponent;
  let fixture: ComponentFixture<PanelCollapsibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanelCollapsibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelCollapsibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
