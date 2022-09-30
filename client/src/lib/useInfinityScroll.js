import { useEffect } from 'react';

const useInfinityScroll = (obsRef, endRef, setPage) => {
  const handlerObs = (entries) => {
    const target = entries[0];

    if (!endRef.current && target.isIntersecting) {
      setPage((prev) => prev + 1);
    }
  };

  useEffect(() => {
    const observer = new IntersectionObserver(handlerObs, { threshold: 0.5 });
    if (obsRef.current) observer.observe(obsRef.current);

    return () => {
      observer.disconnect();
    };
  }, []);
};

export default useInfinityScroll;
