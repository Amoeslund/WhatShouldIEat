export interface RecipeListResponse {
    _embedded: Embedded
    _links: Links3
}

export interface Embedded {
    recipeList: Recipe[]
}

export interface Recipe {
    id: number
    name: string
    recipeStats: RecipeStat[]
    image: string
    recipeTags: RecipeTag[]
    url: string
    _links: Links2
}

export interface RecipeStat {
    label: string
    description: string
}

export interface RecipeTag {
    tag: string
    _links: Links
}

export interface Links {
    self: Self
    "randomByTag": RandomRecipeByTag
    tags: Tags
}

export interface Self {
    href: string
}

export interface RandomRecipeByTag {
    href: string
}

export interface Tags {
    href: string
}

export interface Links2 {
    self: Self
    recipes: Self
}


export interface Recipes {
    href: string
}

export interface Links3 {
    self: Self
}

