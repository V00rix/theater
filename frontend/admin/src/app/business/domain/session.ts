export class Session {
  public readonly type = "session";
  constructor(public id: number,
              public performance: number,
              public hall: number,
              public date: Date) {
  }
}
