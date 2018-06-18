import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {Animations} from '../../../../../shared/animations/animations';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.scss'],
  animations: Animations.animations
})
export class ViewerComponent implements OnInit {
  public contactRegex = /(^((\+[0-9][0-9][0-9][ -]?)?[0-9][0-9][0-9][ -]?[0-9][0-9][0-9][ -]?[0-9][0-9][0-9])$)|(([a-zA-Z0-9.])*@([a-zA-Z])*\.([a-zA-Z])+$)/;
  public nameRegex = /^(([a-z -'`A-Z]+)|([а-я -'`А-Я]+)|([a-z -'`A-ZěščřžýáíéúůĚŠČŘŽÝÁÍÉÚŮ]+))$/;
  public nameValid: boolean;
  public contactValid: boolean;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    if (this.checkValidity()) {
      this.data.updateSelection({client: this.data.applicationStatus.client});
      this.router.navigate(['/checkout']);
    }
  }

  onNameValidated(value) {
    this.nameValid = value;
  }

  onContactValidated(value) {
    this.contactValid = value;
  }

  checkValidity() {
    return this.data.applicationStatus.client &&
      this.data.applicationStatus.client.name && this.data.applicationStatus.client.contact
      &&  this.data.applicationStatus.client.name.match(this.nameRegex) && this.data.applicationStatus.client.contact.match(this.contactRegex);
  }
}
