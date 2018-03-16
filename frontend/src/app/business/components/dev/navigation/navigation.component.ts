import {Component, OnInit} from '@angular/core';
import {PagesService} from '../../../services/pages.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  public pages: String[] = [];

  constructor(public pagesService: PagesService) {
    this.pages = pagesService.pages;
  }

  ngOnInit() {
  }

}
