export class DateMapper {
  public static mapDate(date: string): Date {
    return new Date(date.replace(/-/g, '/'));
  }
}
