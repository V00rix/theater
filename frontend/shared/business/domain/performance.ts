import {Session} from './session';

export class Performance {
  constructor(public id: number, public title: string, public bg: string, public description: string, public sessions: Session[]) {
  }
}
