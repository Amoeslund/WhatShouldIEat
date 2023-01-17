// components/layout.js

import useSWR, {SWRResponse} from 'swr'
import {Recipe, RecipeListResponse} from "@/lib/types/RecipeListResponse";
import RecipeComponent from "@/components/recipe";


type RandomRecipeListProps = {
    recipeList: Recipe[]
}

export default function RecipeList({recipeList}: RandomRecipeListProps) {

    return recipeList?(
        <>
            {recipeList.map((r) => (
                <RecipeComponent key={r.id} recipe={r}/>
            ))}
        </>)
        : <div>Der gik noget galt</div>

}
