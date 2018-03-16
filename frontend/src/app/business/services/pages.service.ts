import {Injectable} from '@angular/core';

@Injectable()
export class PagesService {
  public pages = ['home', 'confirmation', 'contacts', 'performance', 'performances', 'session', 'sessions', 'success', 'viewer'];

  constructor() {
  }

}
