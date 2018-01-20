import {Injectable} from '@angular/core';
import {Performance} from '../models/performance';
import {Subject} from 'rxjs/Subject';


@Injectable()
export class DataManagementService {
  public performances: Performance[];

  constructor() {
    // async http
    this.performances = [];
    this.performances.push(
      new Performance('SomeWord', null, null, 'https://wallpapercave.com/wp/nsu3cSp.jpg'),
      new Performance('Short title', null, null,
        'https://hdwallsource.com/img/2014/8/green-background-pictures-17225-17781-hd-wallpapers.jpg'),
      new Performance('Title with some words', null, null,
        'http://www.creativehdwallpapers.com/uploads/large/background/landscape-nature-background-image.jpg'),
      new Performance('And thus we fall', null, null, 'http://hdwarena.com/wp-content/uploads/2016/12/Color-Codes-Background.jpg')
    );
    console.log('DMS Loaded!');
  }

}
