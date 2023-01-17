// components/layout.js

import {Recipe} from "@/lib/types/RecipeListResponse";
import styles from '@/styles/Home.module.css'

type RecipeProps = {
    recipe: Recipe
}
export default function RecipeComponent({recipe}: RecipeProps) {
    return <>
        <div className={styles.recipe}>
            <h2>{recipe.name}</h2>
            <div>
                {recipe.recipeStats.map(x => x.label + ": " + x.description + " ")}
            </div>
            {/* eslint-disable-next-line @next/next/no-img-element */}
            <a className={styles.recipeImage} href={recipe.url}> <img src={recipe.image} alt={recipe.name}
                                                                      width={500}></img></a>

        </div>
    </>
}
