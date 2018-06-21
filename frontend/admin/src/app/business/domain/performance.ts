import {Checkout} from '../../../../../shared/business/domain/enumeration/checkout';

export class Performance {
  readonly type = "performance";
  constructor(public id: number,
              public author: string,
              public title: string,
              public description: string,
              public imageUrl: string) {
  }
}
