import Head from 'next/head'
import styles from '@/styles/Home.module.css'
import useSWR, {SWRResponse} from "swr";
import {RecipeListResponse} from "@/lib/types/RecipeListResponse";
import RecipeList from "@/components/recipeList";

// @ts-ignore
const fetcher = (...args) => fetch(...args).then((res) => res.json())

export default function Home() {

    const {
        data,
        error
    }: SWRResponse<RecipeListResponse> = useSWR(`/api/recipe/random/${5}`, fetcher, {revalidateOnFocus: false})

    if (error) return <div>Failed to load</div>
    if (!data) return <div>Loading...</div>

    return (
        <>
            <Head>
                <title>What should I eat</title>
                <meta name="description" content="Never look through recipe sites again!"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>
            <main className={styles.main}>
                <div className={styles.center}>
                    <RecipeList recipeList={data?._embedded?.recipeList}/>
                </div>
            </main>
        </>
    )
}
