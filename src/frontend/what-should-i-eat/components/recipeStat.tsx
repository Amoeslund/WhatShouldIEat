// components/layout.js

import {RecipeStat} from "@/lib/types/RecipeListResponse";

type RecipeStatProps = {
    recipeStat: RecipeStat
}

enum TAGS_TO_SHOW {"Tid i alt", }

export default function RecipeStatComponent({recipeStat}: RecipeStatProps) {
    if (recipeStat.label === "Antal")
        return <></>
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
