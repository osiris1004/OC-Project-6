import { ITheme } from "./ITheme";

export interface IArticle{
    id: number;
    title: string;
    content: string;
    created_at: Date;
    updated_at: Date;
    author: string;
    articleThemes: ITheme[];
    comment: IComment[]
}