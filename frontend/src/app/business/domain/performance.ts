import {Session} from './session';

export class Performance {
  constructor(public title: string, public bg: string, public description: string, public sessions: Session[]) {
  }
}
