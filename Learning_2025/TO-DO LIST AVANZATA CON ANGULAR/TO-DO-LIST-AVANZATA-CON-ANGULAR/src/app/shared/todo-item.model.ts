export interface TodoItem {

    id: number ;
    title: string;
    description: string;
    done: boolean;
    category: 'work' | 'family' | 'hobby' | "Categoria";
}
