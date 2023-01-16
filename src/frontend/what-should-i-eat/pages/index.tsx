import Head from 'next/head'
import Image from 'next/image'
import {Inter} from '@next/font/google'
import styles from '@/styles/Home.module.css'
import RandomRecipe from "@/components/recipe";

const inter = Inter({subsets: ['latin']})

export default function Home() {
    return (
        <>
            <Head>
                <title>What should I eat</title>
                <meta name="description" content="Never look through recipe sites again!"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>
            <main className={styles.main}>
                <div className={styles.description}>
                    <p>
                        Created by&nbsp;
                        <code className={styles.code}><a href={"https://www.linkedin.com/in/amoeslund/"}> Anders
                            Bøgetoft Moeslund</a></code>
                    </p>
                </div>

                <div className={styles.center}>
                    <RandomRecipe/>
                </div>
                <div>

                </div>
            </main>
        </>
    )
}
