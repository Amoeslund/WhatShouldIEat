// components/layout.js

import {Recipe} from "@/lib/types/RecipeListResponse";
import styles from '@/styles/Home.module.css'
import RecipeStatComponent from "@/components/recipeStat";

type RecipeProps = {
    recipe: Recipe
}

export default function RecipeComponent({recipe}: RecipeProps) {
    return <>
        <div className={styles.recipe}>
            <h2>{recipe.name}</h2>
            <div className={styles.recipeStats}>
                {recipe.recipeStats.map((r) => (
                    <RecipeStatComponent key={r.label} recipeStat={r}/>
                ))}
            </div>
            <div>
                {/* eslint-disable-next-line @next/next/no-img-element */}
                <a className={styles.recipeImage} href={recipe.url}  target="_blank" rel="noreferrer">
                    <img className={styles.recipeImage} src={recipe.image} alt={recipe.name}/>
                </a>
            </div>
        </div>
    </>
}
