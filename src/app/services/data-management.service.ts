import {Injectable} from '@angular/core';
import {Performance} from '../models/performance';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map'

@Injectable()
export class DataManagementService {
  public performances: Performance[];

  constructor(private http: HttpClient) {
    console.log('DMS Loaded!');
  }

  /**
   * Gets performances titles and background images locators
   * @returns Promise
   */
  getBasicData() {
    console.log('Getting basic data!');
    return this.http.get('http://localhost:80/theater/src/php/getBasicData.php').map(
      data => {
        this.performances = <Performance[]>data;
        console.log(this.performances);
      }, error => {
        console.error(error);
      }
    );
  }
}
