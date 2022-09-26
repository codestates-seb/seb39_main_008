import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getBookList } from '../lib/axios';

const Userpage = () => {
  const { memberId } = useParams();
  const [books, setBooks] = useState([]);

  useEffect(async () => {
    const res = await getBookList(memberId);
    setBooks(res.data);
  }, []);
  return (
    <div>
      {books.map((e) => (
        <div key={e.id}>
          <img alt={e.nickname} src={e.image}></img>
          <p>{e.title}</p>
          <p>{e.subtitle}</p>
        </div>
      ))}
    </div>
  );
};

export default Userpage;
