import {Injectable} from '@angular/core';
import {Performance} from '../models/performance';


@Injectable()
export class DataManagementService {
  public performances: Performance[];

  constructor() {
    // async http
    this.performances = [];
    this.performances.push(
      new Performance(null, null, null, 'https://wallpapercave.com/wp/nsu3cSp.jpg'),
      new Performance(null, null, null, 'https://hdwallsource.com/img/2014/8/green-background-pictures-17225-17781-hd-wallpapers.jpg'),
      new Performance(null, null, null, 'http://www.creativehdwallpapers.com/uploads/large/background/landscape-nature-background-image.jpg'),
      new Performance(null, null, null, 'http://hdwarena.com/wp-content/uploads/2016/12/Color-Codes-Background.jpg')
    );
    console.log('DMS Loaded!');
  }

}
