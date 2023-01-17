// components/layout.js

import useSWR, {SWRResponse} from 'swr'
import {RecipeListResponse} from "@/lib/types/RecipeListResponse";
import styles from '@/styles/Home.module.css'

// @ts-ignore
const fetcher = (...args) => fetch(...args).then((res) => res.json())

export default function RandomRecipe() {
    const {
        data,
        error
    }: SWRResponse<RecipeListResponse> = useSWR('/api/randomRecipe', fetcher, {revalidateOnFocus: false})

    if (error) return <div>Failed to load</div>
    if (!data) return <div>Loading...</div>

    const recipe = data._embedded.recipeList.at(0)!;
    return (
        <>
            <div className={styles.recipe}>
                <h1>{recipe.name}</h1>
                {/* eslint-disable-next-line @next/next/no-img-element */}
                <a href={recipe.url}> <img src={recipe.image} alt={recipe.name} width={800}></img></a>
                <div>
                    {recipe.recipeStats.map(x => x.label + ": " + x.description + " ")}
                </div>
            </div>
        </>
    )
}
