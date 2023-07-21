interface IUser {
    id: number;
    name: string;
    email: string;
    password: string;
    created_at: string;
    updated_at: string;
    subscribedThemes:  {id: number;title: string;content: string}[];
    username: string;
}