// components/layout.js

import {RecipeStat} from "@/lib/types/RecipeListResponse";

type RecipeStatProps = {
    recipeStat: RecipeStat
}

export default function RecipeStatComponent({recipeStat}: RecipeStatProps) {
    return <>
        <div>
            <div>
                {recipeStat.label + " "}
            </div>
            <div>
                {recipeStat.description}
            </div>
        </div>
    </>
}
