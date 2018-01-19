import {Session} from './session';

export class Performance {
  constructor(public title: String,
              public description: String,
              public sessions: Session[],
              public backgroundUrl: String = null) {
  }
}
