import { HttpErrorResponse } from '@angular/common/http';

export class Error {
  constructor(public title: string, public description: string, public error?: any) {
  }

  public static map(res: HttpErrorResponse): Error {
    return {title: res.name, description: res.error.error.message, error: res};
  }
}
