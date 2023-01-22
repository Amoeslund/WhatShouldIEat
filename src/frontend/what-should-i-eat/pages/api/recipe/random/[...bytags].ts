import {NextApiRequest, NextApiResponse} from 'next'
import {RecipeListResponse} from "@/lib/types/RecipeListResponse";


export default async function handler(req: NextApiRequest, res: NextApiResponse<RecipeListResponse>) {

    let query = req.query.bytags;
    if (!Array.isArray(query))
        query = ['1, Aftensmad'];

    const [count, ...tags] = query;

    const json = await fetch(`https://what-should-i-eat-375008.ey.r.appspot.com/recipes/random/${count}/bytags/${tags ?? 'Aftensmad'}`, {
        method: req.method,
        redirect: 'manual',
    }).then(response => {
        console.warn(response);
        return response.json()
    })
        .catch(x => console.warn(x.toString()));

    res.status(200).json(json);
}
