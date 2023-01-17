// components/layout.js

import useSWR, {SWRResponse} from 'swr'
import {RecipeListResponse} from "@/lib/types/RecipeListResponse";
import Image from "next/image";
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

    const {name, url, image} = data._embedded.recipeList.at(0)!;
    return (
        <>
            <div className={styles.recipe}>
                <h1>{name}</h1>
                <a href={url}> <Image src={image} alt={name} width={500} height={500}></Image></a>
            </div>
        </>
    )
}
