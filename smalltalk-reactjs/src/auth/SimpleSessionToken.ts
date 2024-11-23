export class SimpleSessionToken {
  token: string;
  userId: string;
  userName: string;

  constructor(token: string, userId: string, userName: string) {
    this.token = token;
    this.userId = userId;
    this.userName = userName;
  }

  public getToken(): string {
    return this.token;
  }

  public setToken(token: string): void {
    this.token = token;
  }

  public getUserId(): string {
    return this.userId;
  }

  public setUserId(userId: string): void {
    this.userId = userId;
  }

  public getUserName(): string {
    return this.userName;
  }

  public setUserName(userName: string): void {
    this.userName = userName;
  }
}
