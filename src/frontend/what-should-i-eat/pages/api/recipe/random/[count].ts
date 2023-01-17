import {NextApiRequest, NextApiResponse} from 'next'
import {RecipeListResponse} from "@/lib/types/RecipeListResponse";


export default async function handler(req: NextApiRequest, res: NextApiResponse<RecipeListResponse>) {
    const {count} = req.query

    const json = await fetch(`https://what-should-i-eat-375008.ey.r.appspot.com/recipes/random/${count}/bytags/Aftensmad`, {
        method: req.method,
        redirect: 'manual',
    }).then(response => response.json());

    res.status(200).json(json);
}
