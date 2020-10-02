import React, {useCallback, useState} from 'react';
import './App.css';
import Contacts from "./startPage/Contacts";
import './startPage/Toggle.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import HeaderMenu from "./startPage/HeaderMenu";


export const App = () => {
    const logo = 'https://s3.us-west-1.amazonaws.com/com.soar.p/images/profile/companylogos/iTechArt-7926.jpg';
    const companyName = 'Recruiting agency';
    const infCom = 'Разнообразный и богатый опыт консультация с&nbsp; активом обеспечивает широкому кругу. ' +
        '12 лет опыта. Повседневная практика показывает, что реализация намеченных плановых заданий в значительной ' +
        'степени обуславливает создание превосходной модели развития в будующем.';

    let [lang, setLang] = useState('ru');
    const servsName = ['КЛАССИЧЕСКИЙ РЕКРУТИНГ', 'EXECUTIVE SEARCH', 'АБОНЕНТСКИЙ РЕКРУТИНГ'];
    const servs = ['Подбор руководителей и специалистов на типовые позиции. Активный поиск кандидатов и отбор лучших среди тех, кто готов рассматривать предложения от работодателей и хочет сменить работу.',
        'Целевой поиск топ-менеджеров и уникальных специалистов. Особенность услуги в том, что вы имеете возможность выбрать кандидатов из небольшого числа специалистов, работающих на предприятиях вашей отрасли. Как правило, специалисты, из которых производится отбор, не находятся в поиске работы.,' +
        'Закрытие всех ваших вакансий за фиксированную ежемесячную плату. Консультационная поддержка по вопросам управления персоналом.'];
    const employer = 'Company name';
    const employerEmail = 'employer_email@gmail.com';
    const employeeName = 'Alex Kov';

    const handler = useCallback(() => {

        if (lang === 'en') setLang('ru');
        else setLang('en');
    }, [lang]);




    return (

        <section className="App">
            <input className="checkbox" type="checkbox" id="codepen" onChange={handler}/>
            <label htmlFor="codepen"/>

            <HeaderMenu logo={logo} lang={lang} id='1' />

            <Contacts/>
        </section>

    )
}

export default App;

