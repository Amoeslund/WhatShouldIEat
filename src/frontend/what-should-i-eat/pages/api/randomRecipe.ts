import {type NextRequest} from 'next/server'

export const config = {
    runtime: 'edge',
}

export default async function handler(req: NextRequest) {
    return fetch("https://what-should-i-eat-375008.ey.r.appspot.com/recipes/random/bytags/Aftensmad", {
        method: req.method,
        redirect: 'manual',
    })
}
