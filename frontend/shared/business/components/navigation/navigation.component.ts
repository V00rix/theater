import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DataService} from '../../services/data.service';
import {SlidePanelContentComponent} from "../slide-panel/slidePanelContent/slide-panel-content.component";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent extends SlidePanelContentComponent {
  public pages: String[] = [];

  constructor(public pagesService: DataService) {
    super();
    this.pages = pagesService.pages;
  }
}
