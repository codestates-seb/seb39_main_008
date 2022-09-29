import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.bubble.css';

const TextEditor = ({ content, onChange, ...props }) => {
  const modules = {
    toolbar: [
      [{ color: [] }, { background: [] }],
      ['blockquote'],
      [{ list: 'ordered' }, { list: 'bullet' }],
      [{ indent: '-1' }, { indent: '+1' }],
      [{ direction: 'rtl' }], // text direction
      [{ size: ['small', 'large', 'huge'] }],
      [{ header: [1, 2, 3] }],
      [{ font: [] }],
      [{ align: [] }],
      ['clean'],
    ],
  };

  return (
    <ReactQuill
      theme="bubble"
      value={content}
      placeholder="당신의 오늘을 공유해 주세요. 글을 드래그하면 멋진 효과들을 만날 수 있답니다!"
      modules={modules}
      onChange={onChange}
      readOnly={props.viewer}
    />
  );
};

export default TextEditor;
